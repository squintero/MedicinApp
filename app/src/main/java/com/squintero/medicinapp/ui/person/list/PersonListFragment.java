package com.squintero.medicinapp.ui.person.list;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squintero.medicinapp.R;
import com.squintero.medicinapp.data.model.PersonData;
import com.squintero.medicinapp.databinding.FragmentPersonListBinding;
import com.squintero.medicinapp.ui.base.BaseFragment;
import com.squintero.medicinapp.ui.person.PersonActivity;
import com.squintero.medicinapp.ui.person.PersonListener;
import com.squintero.medicinapp.ui.person.PersonView;

import java.util.List;


public class PersonListFragment extends BaseFragment implements
        PersonView.PersonList, PersonListAdapter.PersonListListener {

    private static final int NUM_COLUMS     = 1;

    private View view;
    private AppCompatActivity mActivity;
    private PersonActivity activityView;
    private FragmentPersonListBinding mBinding;
    private PersonListPresenterImpl presenter;

    public static PersonListFragment newInstance() {

        PersonListFragment fragment = new PersonListFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);

        return fragment;
    }

    public void readBundle(Bundle arguments) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof AppCompatActivity) {
            mActivity    = (AppCompatActivity) context;
            activityView = (PersonActivity) mActivity;
        }

        presenter = new PersonListPresenterImpl(mActivity, this);
        presenter.attachView(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mActivity    = null;
        activityView = null;
        presenter.detachView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        readBundle(getArguments());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_person_list, container, false);
        view     = mBinding.getRoot();
        mBinding.setView(this);
//        mBinding.incToolbarTopCollapsing.setListener(onClickAction());

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

        loadList();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        //TODO ocultar boton "crear peticion"
//        activityView.toolbarBuyButtonHide();
    }

    @Override
    public View.OnClickListener onClickAction() {
        return new PersonListener(mActivity, activityView, this).PersonList;
    }

    /** PersonView.PersonList **/
    @Override
    public void loadList() {
        presenter.getList();
    }


    @Override
    public void addAction() {
        //TODO
    }

    @Override
    public void populateList(List<PersonData> personDataList) {
        if (!isValidList(personDataList))
            return;

        PersonListAdapter adapter = new PersonListAdapter(personDataList, this);
        mBinding.rvList.setLayoutManager(new GridLayoutManager(mActivity, NUM_COLUMS));
        mBinding.rvList.setAdapter(adapter);
        mBinding.rvList.setHasFixedSize(true);

//        if (getActivity() != null)
//            if (getActivity() instanceof ListActivity)
//                ((ListActivity) getActivity()).prepareSearchView(adapter, role);
    }

    @Override
    public void updateList(List<PersonData> personDataList) {
        if (!isValidList(personDataList))
            return;

        PersonListAdapter adapter = (PersonListAdapter) mBinding.rvList.getAdapter();

        if (adapter != null)
            adapter.updateData(personDataList);
    }

    private boolean isValidList(List<PersonData> personDataList){
        if (personDataList.isEmpty()){
            mBinding.tvEmptyList.setVisibility(View.VISIBLE);
            mBinding.rvList.setVisibility(View.GONE);
            return false;
        }
        mBinding.tvEmptyList.setVisibility(View.GONE);
        mBinding.rvList.setVisibility(View.VISIBLE);
        return true;
    }

    /** PersonListAdapter.PersonListListener **/
    @Override
    public void onItemClick(PersonData personData, int position) {
        presenter.onClickPerson(personData);
    }
}
