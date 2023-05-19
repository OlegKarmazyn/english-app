package solid.icon.english.navigation_menu.account

//todo:
// ==================================
// 1 delete verification email
// 1.1 change rules in firebase db
// ✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓
// 2. create registration layout
//    with fields like in web
//    with button + already have acc
// ✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓
// 3. create activity with title reg
//    and home button
// ✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓
// 4. create func which gets data
//    from fields (with validation)
//    when click button
//    then call future post func
// ✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓
// 5. on already have acc button set
//    onClick and move to auth
// ✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓
// 6. create viewModel and model
// ✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓
// 7. create func which post users'
//    data to database
// ✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓
// 8. create in viewModel:
//    • initialization two models:
//      Registration Model
//    • func registerUser with param
//      of all fields then call two
//      models func (post and reg)
// ==================================
// 9.1 rename AccountAct to AuthAct
//    then redone authentication
// 9.2 create and set up
//     forgetPassword button
//     maybe showDialog with editText
// ✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓✓
// 10. after auth move to the new
//     AccountActivity
// ==================================
// 11. AccountActivity:
//     • create layout with fields like
//     in web with buttons without
//     chat button
//     • home button
// ----------------------------------
// 12. create ViewModel
// ----------------------------------
// 13. create AccountModel which can:
//     get users' data -> related to
//     create UserProfileItem with
//     variables of personal data
// ----------------------------------
// 14. create in ViewModel:
//     • func getProfile() which
//       returns UserProfileItem
// ----------------------------------
// 15. define in View func:
//     • setProfile() which calls
//       getProfile form ViewModel
//       and setData to components
// ----------------------------------
// 16. set listeners to buttons:
//     • id = showCalendar
//     • id = showBudget ->
//       move to the next Activity
// ==================================
// 17. create:
//      • BudgetActivity + home button
//      • layout with recycler and
//        paidLessons field
//      • layout with history cell
//      • RecyclerAdapter
// ----------------------------------
// 18. create HistoryItem
// ----------------------------------
// 19. set up getting data and set up
//     RecyclerAdapter + lessons field
// ----------------------------------
// 20.