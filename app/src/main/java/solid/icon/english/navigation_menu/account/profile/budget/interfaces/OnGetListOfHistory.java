package solid.icon.english.navigation_menu.account.profile.budget.interfaces;

import java.util.List;

import solid.icon.english.navigation_menu.account.profile.budget.firebase.HistoryModel;

public interface OnGetListOfHistory {
    void onSuccess(List<HistoryModel> list);
    void onStart();
}
