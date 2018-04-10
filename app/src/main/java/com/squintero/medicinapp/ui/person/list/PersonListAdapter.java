package com.squintero.medicinapp.ui.person.list;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.squintero.medicinapp.BR;
import com.squintero.medicinapp.R;
import com.squintero.medicinapp.data.model.PersonData;

import java.util.ArrayList;
import java.util.List;


public class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.DataHolder> implements Filterable {

    private List<PersonData> data;
    private List<PersonData> filteredData;
    private PersonListListener listener;

    public PersonListAdapter(List<PersonData> data, PersonListListener listener) {
        this.data           = data;
        this.filteredData   = data;
        this.listener       = listener;
    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        LayoutInflater inflater  = LayoutInflater.from(viewGroup.getContext());

        ViewDataBinding mBinding = DataBindingUtil.inflate(inflater, R.layout.item_person, viewGroup, false);

        return new DataHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull DataHolder holder, final int position) {

        PersonData personData = filteredData.get(position);

        holder.getBinding().setVariable(BR.model, personData);
//        holder.getBinding().setVariable(BR.date, Utils.formatDateFromServer(personData.getCreatedAt(), false));
        holder.getBinding().setVariable(BR.onClickListener, onClickAction(personData, position));
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        if (filteredData == null)
            return 0;

        return filteredData.size();
    }

    public void updateData(List<PersonData> newData) {

        if (newData == null) {
            return;
        }

        data            = newData;
        filteredData    = newData;
        notifyDataSetChanged();
    }

    public View.OnClickListener onClickAction(final PersonData personData, final int position) {

        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (view.getId()) {

                    case R.id.root:
                        if (listener != null)
                            listener.onItemClick(personData, position);
                        break;
                }
            }
        };
    }

    /** HOLDER **/
    public static class DataHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding mBinding;

        public DataHolder(ViewDataBinding mBinding) {
            super(mBinding.getRoot());

            this.mBinding = mBinding;
            setIsRecyclable(false);
        }

        public ViewDataBinding getBinding() {
            return mBinding;
        }
    }

    /** LISTENER **/
    public interface PersonListListener {

        void onItemClick(PersonData personData, int position);
    }

    /** Filterable **/
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filteredData = data;
                } else {
                    List<PersonData> filteredList = new ArrayList<>();
                    for (PersonData row : data) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (    (row.getName() != null && row.getName().toLowerCase().contains(charString.toLowerCase()))
                             || (row.getSurname() != null && row.getSurname().toLowerCase().contains(charString.toLowerCase()))
                           )
                        {
                            filteredList.add(row);
                        }
                    }

                    filteredData = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredData;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredData = (List<PersonData>) filterResults.values;

                // refresh the list with filtered data
                notifyDataSetChanged();
            }
        };
    }
}
