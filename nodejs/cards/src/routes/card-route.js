const api = require('../controllers/card-controller')

module.exports = (app) => {
    app.route('/cards')
        .get(api.findAll)
        .post(api.save)
    app.route('/cards/id')
        .get(api.findOne)
        .put(api.updateOne)
        .delete(api.removeOne)
    app.route('/paginationAndSorting')
        .get(api.pagenationAndSorting)
}