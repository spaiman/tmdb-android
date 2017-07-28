package com.setiawanpaiman.tmdb.android.data.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Setiawan Paiman on 24/6/17.
 */

public class PaginatedList<ApiModel extends IViewModel<ViewModel>, ViewModel> {

    private List<ApiModel> results;

    public List<ViewModel> toListViewModel() {
        List<ViewModel> listViewModel = new ArrayList<>();
        for (ApiModel apiModel : results) {
            ViewModel vm = apiModel.toViewModel();
            if (vm != null) {
                listViewModel.add(vm);
            }
        }
        return listViewModel;
    }
}
