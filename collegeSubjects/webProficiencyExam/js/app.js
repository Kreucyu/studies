let medidorAlbumId = localStorage.getItem('medidorAlbumId') ? parseInt(localStorage.getItem('medidorAlbumId')) : 1

function calcularAlbumId(medidorAlbumId) {
    if (medidorAlbumId == 3) {
        return true
    }
    return false
}

async function getIdDisponivel() {
    try {
        const response = await fetch('https://jsonplaceholder.typicode.com/photos')
        const photos = await response.json()
        const apiPhotos = photos || [];
        const apiID = apiPhotos.length ? Math.max(...apiPhotos.map(photo => photo.id)) : 0

        const localPhotos = []

        for (let i = 0; i < localStorage.length; i++) {
            let chave = localStorage.key(i)

            if (chave.startsWith('photo_') || chave.startsWith('altL_photo_') && !chave.startsWith('del_photo')) {
                let photo = JSON.parse(localStorage.getItem(chave))
                localPhotos.push(photo)
            }
        }
        const localId = localPhotos.length ? Math.max(...localPhotos.map(photo => photo.id)) : 0
        const proximoId = Math.max(apiID, localId) + 1
        console.log("proximo id " + proximoId)
        return proximoId
    } catch {
        exibirModal(3, 'erro')
        return 1
    }
}
/* Arrumar metodo getAlbumId na medida em que for decrementado ele ter registrado quantas imagens tem por album! 
verificar quando foi apagado uma imagem antes da ultima para saber tambem qual o valor
melhor forma de fazer isso seria salvar quando bater 50 imagens, por mais que seja deletado o 
valor se torna inalteravel, indicando que o album foi preenchido, mas se nao for = a 50 entao podera ser alterado
o valor dependendo da situacao. */
async function getAlbumId() {
    try {
        const response = await fetch('https://jsonplaceholder.typicode.com/photos')
        const photos = await response.json()
        const apiPhotos = photos || [];
        const apiAlbumID = apiPhotos.length ? Math.max(...apiPhotos.map(photo => photo.albumId)) : 0
        const localPhotos = []

        for (let i = 0; i < localStorage.length; i++) {
            let chave = localStorage.key(i)

            if (chave.startsWith('photo_') || chave.startsWith('altL_photo_') && !chave.startsWith('del_photo')) {
                let photo = JSON.parse(localStorage.getItem(chave))
                localPhotos.push(photo)
            }
        }
        const localAlbumId = localPhotos.length ? Math.max(...localPhotos.map(photo => photo.albumId)) : 0
        let calcular = calcularAlbumId(medidorAlbumId)
        if (calcular) {
            medidorAlbumId = 1
            localStorage.setItem('medidorAlbumId', medidorAlbumId)
        }
        if (medidorAlbumId == 1) {
            proximoAlbumId = Math.max(apiAlbumID, localAlbumId) + 1
        } else {
            proximoAlbumId = Math.max(apiAlbumID, localAlbumId)
        }
        localStorage.setItem(`albumId`, proximoAlbumId)
        console.log("proximo album id " + proximoAlbumId)
        console.log(`saida ` + medidorAlbumId)
        return proximoAlbumId
    } catch {
        exibirModal(3, 'erro')
        return 1
    }
}

function confirmarDelete(value) {
    return new Promise((resolve, reject) => {
        const confirmacao = document.getElementById('botao-delete')
        const confirmacaoHandler = () => {
            limparBotoes()
            resolve(true)
        }
        const voltar = document.getElementById('botao-config')
        const voltarHandler = () => {
            limparBotoes()
            resolve(false)
        }
        confirmacao.addEventListener('click', confirmacaoHandler)
        voltar.addEventListener('click', voltarHandler)

        function limparBotoes(params) {
            confirmacao.removeEventListener('click', confirmacaoHandler)
            voltar.removeEventListener('click', voltarHandler)
        }
    })
}

class Photo {
    constructor(id, albumId, title, url, thumbnailUrl) {
        this.id = id
        this.albumId = albumId
        this.title = title
        this.url = url
        this.thumbnailUrl = thumbnailUrl
    }

    validarDados() {
        for (let i in this) {
            if (this[i] == null || this[i] == '' || this[i] == undefined) {
                console.log(this[i])
                return false
            }
        }
        return true
    }
}

let funcao = 0

function limitar(value, confirm) {
    document.getElementById('drop').innerHTML = value
    let titleAcesso = document.getElementById('title')
    let urlAcesso = document.getElementById('url')
    let idAlbumAcesso = document.getElementById('albumId')
    let thumbAcesso = document.getElementById('thumbnailUrl')
    let idAcesso = document.getElementById('id')
    if (value == 'Adicionar imagem') { thumbAcesso.disabled = false, idAcesso.disabled = true, urlAcesso.disabled = false, titleAcesso.disabled = false, idAlbumAcesso.disabled = true, funcao = 1, limparCaixas() }
    if (value == 'Alterar imagem') { thumbAcesso.disabled = false, idAcesso.disabled = false, urlAcesso.disabled = false, titleAcesso.disabled = false, idAlbumAcesso.disabled = true, funcao = 2, limparCaixas() }
    if (value == 'Consultar imagem') { thumbAcesso.disabled = true, idAcesso.disabled = false, urlAcesso.disabled = true, titleAcesso.disabled = true, idAlbumAcesso.disabled = false, funcao = 3, limparCaixas() }
    if (value == 'Deletar imagem') { thumbAcesso.disabled = true, idAcesso.disabled = false, urlAcesso.disabled = true, titleAcesso.disabled = true, idAlbumAcesso.disabled = true, funcao = 4, limparCaixas() }
    if (value == 'O que deseja fazer?') { thumbAcesso.disabled = true, idAcesso.disabled = true, urlAcesso.disabled = true, titleAcesso.disabled = true, idAlbumAcesso.disabled = true }

    if (confirm) {
        switch (funcao) {
            case 1:
                funcoes(1)
                break;
            case 2:
                funcoes(2)
                break;
            case 3:
                let paginas = document.getElementById('paginas')
                paginas.innerHTML = ''
                consultarImagem()
                break;
            case 4:
                deletarImagem()
                break;
        }
        funcao = 0
    }
}

async function funcoes(result) {
    let title = document.getElementById('title').value
    let url = document.getElementById('url').value
    let thumbnailUrl = document.getElementById('thumbnailUrl').value
    let albumId = await getAlbumId()

    result == 1 ? await adicionarImagem() : await alterarImagem()

    async function adicionarImagem() {
        let id = await getIdDisponivel()
        let photo = new Photo(id, albumId, title, url, thumbnailUrl)
        console.log(photo)
        try {
            if (photo.validarDados()) {
                const response = await fetch('https://jsonplaceholder.typicode.com/photos', {
                    method: 'POST',
                    headers: {
                        'Content-type': 'application/json'
                    },
                    body: JSON.stringify({
                        albumId: albumId,
                        id: id,
                        title: title,
                        url: url,
                        thumbnailUrl: thumbnailUrl
                    })
                })
                if (!response.ok) throw new Error('Erro na API')
                const photos = await response.json();

                localStorage.setItem(`photo_${photo.id}`, JSON.stringify(photo))
                localStorage.setItem('id', id)
                medidorAlbumId++
                localStorage.setItem('medidorAlbumId', medidorAlbumId)
                exibirModal(1, "sucesso")
                limparCaixas()
            } else {
                exibirModal(1, "erro")
                limparCaixas()
            }
        } catch (error) {
            console.error(error)
        }

    }

    async function alterarImagem() {
        let id = document.getElementById('id').value
        if (id != "" && id != null && id != undefined) {
            let validarId = await verificarId(id)
            if (!validarId) {
                limparCaixas()
                exibirModal(3, "erro")
                return
            } else {

                let atualizarDados = {}
                if (id) atualizarDados.id = parseInt(id)
                if (title) atualizarDados.title = title
                if (url) atualizarDados.url = url
                if (thumbnailUrl) atualizarDados.thumbnailUrl = thumbnailUrl

                if (validarId.localOnly) {
                    const photoKey = `photo_${id}`
                    const altLKey = `altL_photo_${id}`
                    const valorAtual = (localStorage.getItem(photoKey)) ? JSON.parse(localStorage.getItem(`photo_${id}`)) :
                        (localStorage.getItem(altLKey)) ? JSON.parse(localStorage.getItem(`altL_photo_${id}`)) : null
                    if (!valorAtual) {
                        console.error(`valor nao encontrado!`)
                        return
                    }
                    if (title && title !== valorAtual.title) valorAtual.title = title
                    if (url && url !== valorAtual.url) valorAtual.url = url
                    if (thumbnailUrl && thumbnailUrl !== valorAtual.thumbnailUrl) valorAtual.thumbnailUrl = thumbnailUrl
                    localStorage.removeItem(photoKey)
                    localStorage.setItem(altLKey, JSON.stringify(valorAtual))
                    limparCaixas()
                    exibirModal(2, "sucesso")
                } else if (validarId.foundInAll) {
                    const delKey = `del_photo_${id}`
                    if (localStorage.getItem(delKey)) return exibirModal(3, `erro`)
                    const valorAtual = JSON.parse(localStorage.getItem(`alt_photo_${id}`))
                    if (title && title !== valorAtual.title) valorAtual.title = title
                    if (url && url !== valorAtual.url) valorAtual.url = url
                    if (thumbnailUrl && thumbnailUrl !== valorAtual.thumbnailUrl) valorAtual.thumbnailUrl = thumbnailUrl
                    localStorage.setItem(`alt_photo_${id}`, JSON.stringify(valorAtual))
                    limparCaixas()
                    exibirModal(2, "sucesso")
                } else if (validarId.apiOnly) {
                    try {
                        const response = await fetch(`https://jsonplaceholder.typicode.com/photos/${id}`, {
                            method: 'PATCH',
                            headers: {
                                'Content-type': 'application/json'
                            },
                            body: JSON.stringify(atualizarDados)
                        })
                        console.log(response)

                        if (!response.ok) throw new Error('Erro na API')
                        const atualizacao = await response.json();
                        localStorage.setItem(`alt_photo_${id}`, JSON.stringify(atualizarDados))
                        limparCaixas()
                        exibirModal(2, "sucesso")
                    } catch (error) {
                        console.error(error)
                    }
                }
            }
        } else {
            limparCaixas()
            exibirModal(2, "erro")
            return
        }
    }
}

let paginaAtual = 1
const itensPorPagina = 50

async function consultarImagem() {
    let id = document.getElementById('id').value
    let albumId = document.getElementById('albumId').value
    const inicio = (paginaAtual - 1) * itensPorPagina
    const fim = inicio + itensPorPagina

    if (id !== "" && albumId === "") {
        try {
            let validarId = await verificarId(id)
            if (!validarId) return exibirModal(4, "erro")
            let exibicao = null
            let alterador = `alt_photo_${id}`
            let localAdc = `photo_${id}`
            let localAdcAlt = `altL_photo_${id}`
            const response = await fetch(`https://jsonplaceholder.typicode.com/photos/${id}`)
            console.log(response)
            if (response.ok) exibicao = await response.json()
            if (!exibicao) {
                if (localStorage.getItem(localAdc)) exibicao = JSON.parse(localStorage.getItem(localAdc))
                else if (localStorage.getItem(localAdcAlt)) exibicao = JSON.parse(localStorage.getItem(localAdcAlt))
                else return exibirModal(4, "erro")
            }
            
            
            if (localStorage.getItem(alterador) && exibicao.length > 0) {
                exibicao = { ...exibicao, ...JSON.parse(localStorage.getItem(alterador)) }
            }
            let deletado = `del_photo_${exibicao.id}`
            if (localStorage.getItem(deletado)) {
                exibicao = null
            }
            if (exibicao) exibirTabela([exibicao])
            else exibirTabela([])
        } catch (error) {
            console.error(error)

        }

    }

    else if (id === "" && albumId !== "") {
        try {
            let validarAlbumId = await verificarAlbumId(albumId)
            if (!validarAlbumId) return exibirModal(4, "erro")
            const response = await fetch(`https://jsonplaceholder.typicode.com/photos?albumId=${albumId}`)
            console.log(response)
            if (!response.ok) throw new Error('Erro na API')
            const data = await response.json()
            let filtro = data.filter(i => i.albumId == albumId)
            exibirTabela(filtro)
        } catch (error) {
            console.error(error)
        }

    }

    else {
        try {
            const response = await fetch(`https://jsonplaceholder.typicode.com/photos/`)
            console.log(response)
            if (!response.ok) throw new Error('Erro na API')
            const data = await response.json()
            let exibicao = []
            exibicao = [...data]
            let localAdc = Object.keys(localStorage).filter(key => key.startsWith('photo_')).map(key => JSON.parse(localStorage.getItem(key)))
            localAdc.push(...Object.keys(localStorage).filter(key => key.startsWith('altL_photo_')).map(key => JSON.parse(localStorage.getItem(key))))
            exibicao = [...data, ...localAdc]
            exibicao = exibicao.map(imagem => {
                let alterador = `alt_photo_${imagem.id}`
                if (localStorage.getItem(alterador)) return { ...imagem, ...JSON.parse(localStorage.getItem(alterador)) }
                return imagem
            })
            let exclusoes = Object.keys(localStorage).filter(key => key.startsWith('del_photo_')).map(key => key.replace('del_photo_', ''))
            exibicao = exibicao.filter(imagem => !exclusoes.includes(String(imagem.id)))
            const imagensPaginadas = exibicao.slice(inicio, fim)
            exibirTabela(imagensPaginadas)
            paginacao(data.length)
        } catch (error) {
            console.error(error)
        }
    }
    limparCaixas()

    function paginacao(paginas) {
        const totalPaginas = Math.ceil(paginas / itensPorPagina)
        let paginasDiv = document.getElementById(`paginas`)
        paginasDiv.innerHTML = ``

        if (paginaAtual > 1) {
            let botaoVoltar = document.createElement(`button`)
            botaoVoltar.innerHTML = '<i class="fa-solid fa-angle-left"></i>'
            botaoVoltar.className = "arrows"
            botaoVoltar.onclick = () => {
                paginaAtual--
                consultarImagem()
            }
            paginasDiv.appendChild(botaoVoltar)
        }

        let pAtual = document.createElement('span')
        pAtual.textContent = `Página ${paginaAtual} de ${totalPaginas}`
        paginasDiv.appendChild(pAtual)

        if (paginaAtual < totalPaginas) {
            let botaoAvancar = document.createElement(`button`)
            botaoAvancar.innerHTML = '<i class="fa-solid fa-angle-right"></i>'
            botaoAvancar.className = "arrows"
            botaoAvancar.onclick = () => {
                paginaAtual++
                consultarImagem()
            }
            paginasDiv.appendChild(botaoAvancar)
        }

    }

    function exibirTabela(data) {
        if (!Array.isArray(data)) {
            console.error("os dados recebidos não estão em um array")
            return
        }
        let imagensLista = document.getElementById('imagensLista')
        imagensLista.innerHTML = ''

        data.forEach(i => {
            let inserirLinha = imagensLista.insertRow()
            inserirLinha.insertCell(0).innerHTML = i.id
            inserirLinha.insertCell(1).innerHTML = i.title
            inserirLinha.insertCell(2).innerHTML = i.url
            inserirLinha.insertCell(3).innerHTML = i.thumbnailUrl
            inserirLinha.insertCell(4).innerHTML = i.albumId
        })
    }
}

async function deletarImagem() {
    let id = document.getElementById('id').value
    if (id != "" && id != null && id != undefined) {
        let validarId = await verificarId(id)
        console.log(validarId)
        if (!validarId) {
            limparCaixas()
            exibirModal(3, "erro")
            return
        } else {
            if (validarId.localOnly) {
                exibirModal(1, 'delete')
                const confirmacao = await confirmarDelete()
                if (confirmacao) {
                    const photoKey = `photo_${id}`
                    const altLKey = `altL_photo_${id}`
                    localStorage.removeItem(photoKey)
                    localStorage.removeItem(altLKey)
                    limparCaixas()
                    setTimeout(() => {
                        exibirModal(3, 'sucesso')
                    }, "1000");
                    let idDisp = await getIdDisponivel()
                    if (id == idDisp) {
                        medidorAlbumId--
                        localStorage.setItem('medidorAlbumId', medidorAlbumId)
                    }
                    if (medidorAlbumId < 0) {
                        medidorAlbumId = 0
                        localStorage.setItem('medidorAlbumId', medidorAlbumId)
                    }
                }
            } if (validarId.foundInAll || validarId.apiOnly) {
                exibirModal(1, 'delete')
                const confirmacao = await confirmarDelete()
                if (confirmacao) {
                    try {
                        const response = await fetch(`https://jsonplaceholder.typicode.com/photos/${id}`, {
                            method: 'DELETE',
                            headers: {
                                'Content-type': 'application/json'
                            }
                        })
                        console.log(response)

                        if (!response.ok) throw new Error('Erro na API')
                        if (validarId.foundInAll) localStorage.removeItem(`alt_photo_${id}`)
                        if (validarId.foundInAll) localStorage.removeItem(`photo_${id}`)
                        localStorage.setItem(`del_photo_${id}`, JSON.stringify(parseInt(id)))
                        limparCaixas()
                        exibirModal(3, 'sucesso')

                    } catch (error) {
                        console.error(error)
                    }
                }
            } else {
                limparCaixas()
                exibirModal(3, "erro")
                return
            }
        }
    }
}

async function verificarId(id) {
    function localVerify(id) {
        const photo = localStorage.getItem(`photo_${id}`)
        const altPhoto = localStorage.getItem(`alt_photo_${id}`)
        const altLPhoto = localStorage.getItem(`altL_photo_${id}`)
        const delPhoto = localStorage.getItem(`del_photo_${id}`)
        return photo || altPhoto || altLPhoto || delPhoto ? true : false
    }
    async function apiVerify(id) {
        try {
            const response = await fetch(`https://jsonplaceholder.typicode.com/photos/${id}`)
            if (!response.ok) return false
            const data = await response.json()
            return data && Object.keys(data).length > 0 ? true : false
        } catch {
            console.error(error)
            return false
        }
    }

    const localConfirm = localVerify(id)
    const apiConfirm = await apiVerify(id)

    const resposta = {
        localOnly: localConfirm && !apiConfirm,
        apiOnly: !localConfirm && apiConfirm,
        foundInAll: localConfirm && apiConfirm,
    }

    return resposta
}

async function verificarAlbumId(albumId) {
    function localVerify(albumId) {
        for (let i = 0; i < localStorage.length; i++) {
            const key = localStorage.key(i)

            if (key.startsWith('photo_') || key.startsWith('altL_photo_')) {
                let valor = localStorage.getItem(key)

                if (!valor) {
                    continue
                }
                try {
                    let dados = JSON.parse(valor)
                    return dados && dados.albumId && String(dados.albumId) === String(albumId) ? true : false
                } catch (error) {
                    console.error(error)
                    continue
                }
            }
        }
        return false
    }
    async function apiVerify(albumId) {
        try {
            const response = await fetch(`https://jsonplaceholder.typicode.com/photos?albumId=${albumId}`)
            if (!response.ok) return false
            const data = await response.json()
            return Array.isArray(data) && data.length > 0 ? true : false
        } catch {
            console.error(error)
            return false
        }
    }

    const localConfirm = localVerify(albumId)
    const apiConfirm = await apiVerify(albumId)

    const resposta = {
        localOnly: localConfirm && !apiConfirm,
        apiOnly: !localConfirm && apiConfirm,
        foundInAll: localConfirm && apiConfirm,
    }
    return resposta
}

function consultarConf(escolha) {
    let idAlbumAcesso = document.getElementById('albumId')
    let idAcesso = document.getElementById('id')
    if (escolha == 0 && funcao == 3) {
        idAlbumAcesso.disabled = false
        idAcesso.disabled = true
    } else if (escolha == 1 && funcao == 3) {
        idAlbumAcesso.disabled = true
        idAcesso.disabled = false
    }
    if (idAlbumAcesso.value == '' && idAcesso.value == '' && funcao == 3) {
        idAlbumAcesso.disabled = false
        idAcesso.disabled = false
    }
}

function limparCaixas() {
    document.getElementById('title').value = ''
    document.getElementById('url').value = ''
    document.getElementById('thumbnailUrl').value = ''
    document.getElementById('id').value = ''
    document.getElementById('albumId').value = ''
}

function exibirModal(valor, tipo) {
    document.getElementById('estilo-modal').classList.add('text')
    document.getElementById('botao-config').innerHTML = 'Voltar'
    document.getElementById('botao-delete').style.display = "none"
    if (tipo == 'erro') {
        document.getElementById('title-modal').innerHTML = 'Erro!'
        document.getElementById('conteudo-modal').innerHTML = valor == 1 ? 'Preencha todos os campos!' : valor == 2 ? 'Insira um ID para alterar' : valor == 3 ? 'ID não encontrado' : 'Não foi possível realizar a busca!'
        return $('#exibirModal').modal('show')
    } if (tipo == 'delete') {
        document.getElementById('title-modal').innerHTML = 'Deletar'
        document.getElementById('conteudo-modal').innerHTML = 'Prosseguir com a exclusão?'
        document.getElementById('botao-delete').style.display = "inline"
        document.getElementById('botao-delete').innerHTML = 'Confirmar'
        return $('#exibirModal').modal('show')
    }
    document.getElementById('title-modal').innerHTML = 'Sucesso!'
    document.getElementById('conteudo-modal').innerHTML = valor == 1 ? 'Imagem adicionada com sucesso!' : valor == 2 ? 'Alteração concluída!' : 'Imagem excluída com sucesso!'
    return $('#exibirModal').modal('show')
}
