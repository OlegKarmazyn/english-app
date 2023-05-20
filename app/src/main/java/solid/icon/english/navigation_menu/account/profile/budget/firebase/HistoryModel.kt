package solid.icon.english.navigation_menu.account.profile.budget.firebase

class HistoryModel {
    var description: String? = null
    var date: String? = null

    constructor() {}
    constructor(description: String?, date: String?) {
        this.description = description
        this.date = date
    }
}