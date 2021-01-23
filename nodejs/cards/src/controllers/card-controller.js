const neDB = require('../configurations/database')
const api = {}

api.findAll = (request, response) => {
    neDB.find({}).sort({ name: 1 }).exec((exception, cards) => {
        if (exception) {
            const sentence = 'Deu ruim na tentativa de listar todos os customers!'
            console.log(sentence, exception)
            response.status(exception.status | 400)
            response.json({ 'mensagem': sentence })
        }
        response.status(200)
        response.json(cards)
    })

}

api.save = (request, response) => {
    const canonical = request.body
    neDB.insert(canonical, (exception, card) => {
        if (exception) {
            const sentence = 'Deu ruim na tentativa de salvar o cartão!'
            console.log(sentence, exception)
            response.status(exception.status | 400)
            response.json({ 'mensagem': sentence })
        }
        response.status(201)
        response.json(card)
    })
}

api.findOne = (request, response) => {
    neDB.findOne({ _id: 'E084aLX18RlCqF' }, function (exception, card) {
         if(card == null){
            const sentence = 'Cartão não foi encontrado!'
            console.log(sentence)
            response.status(404)
            response.json({ 'mensagem': sentence })
         }
         if (exception) {
            const sentence = 'Deu ruim na tentativa de achar o cartão!'
            console.log(sentence, exception)
            response.status(exception.status | 400)
            response.json({ 'mensagem': sentence })
         }
         response.status(200)
         response.json(card)
    });
}

api.removeOne = (request, response) => {
    neDB.remove({ _id: 'E084a9X18RlCqF' }, function (exception, card) {
         if (exception) {
            const sentence = 'Deu ruim na tentativa de remover o cartão!'
            console.log(sentence, exception)
            response.status(exception.status | 400)
            response.json({ 'mensagem': sentence })
         }
         response.status(200)
         response.json(card)
    });
}

api.updateOne = (request, response) => {
    neDB.update({ _id: 'YlZKqrAzzHrIFF' },{ $set: { customerName: 'Josephina', motherName: "Ciclana" } }, { multi: true }, function (exception, card) {
         if (exception) {
            const sentence = 'Deu ruim na tentativa de atualizar as informações do cartão!'
            console.log(sentence, exception)
            response.status(exception.status | 400)
            response.json({ 'mensagem': sentence })
         }
         response.status(200)
         response.json(card)
    });
}

module.exports = api