package com.example.task.Adapters;

    import android.app.Activity;
    import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task.Models.Menu;
import com.example.task.Models.StoreMenu;
import com.example.task.R;
import com.example.task.Utils.TotalListener;
    import com.example.task.Utils.constants;
    import com.example.task.databinding.ContentStoreMenuRvBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StoreMenuAdapter extends BaseExpandableListAdapter {

    private  List<List<Menu>> childItems=new ArrayList<>();
    private List<StoreMenu> parentItems;
    //    private final ArrayList<HashMap<String, String>> childItems;
    private LayoutInflater inflater;
    private Activity activity;
    private Menu child;
    private int count = 0;
    private boolean isFromMyCategoriesFragment;

    public StoreMenuAdapter(Activity activity, List<StoreMenu> parentItems,boolean isFromMyCategoriesFragment) {
        this.parentItems = parentItems;
        this.activity = activity;
        this.isFromMyCategoriesFragment = isFromMyCategoriesFragment;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for (StoreMenu menu:parentItems){
            childItems.add(menu.getList());
        }
    }

    @Override
    public int getGroupCount() {
        return parentItems.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return (childItems.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, final boolean b, View convertView, ViewGroup viewGroup) {
        final ViewHolderParent viewHolderParent;
        if (convertView == null) {

            if(isFromMyCategoriesFragment) {
                convertView = inflater.inflate(R.layout.content_store_menu_rv, null);
            }else {
                convertView = inflater.inflate(R.layout.content_store_menu_rv, null);
            }
            viewHolderParent = new ViewHolderParent();

            viewHolderParent.tvMainCategoryName = convertView.findViewById(R.id.header);
            viewHolderParent.cbMainCategory = convertView.findViewById(R.id.header_cb);

            convertView.setTag(viewHolderParent);
        } else {
            viewHolderParent = (ViewHolderParent) convertView.getTag();
        }

        if (parentItems.get(groupPosition).isChecked()) {
            viewHolderParent.cbMainCategory.setChecked(true);
            notifyDataSetChanged();

        } else {
            viewHolderParent.cbMainCategory.setChecked(false);
            notifyDataSetChanged();
        }

        viewHolderParent.cbMainCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewHolderParent.cbMainCategory.isChecked()) {
                    parentItems.get(groupPosition).setChecked(true);

                    for (int i = 0; i < childItems.get(groupPosition).size(); i++) {
                        childItems.get(groupPosition).get(i).setChecked( true);
                    }
                    notifyDataSetChanged();

                }
                else {
                    parentItems.get(groupPosition).setChecked(false);
                    for (int i = 0; i < childItems.get(groupPosition).size(); i++) {
                        childItems.get(groupPosition).get(i).setChecked(false);
                    }
                    notifyDataSetChanged();
                }
            }
        });
//
        constants.Lists.childItems = childItems;
        constants.Lists.parentItems = parentItems;

        viewHolderParent.tvMainCategoryName.setText(parentItems.get(groupPosition).getName());

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, final boolean b, View convertView, ViewGroup viewGroup) {

        final ViewHolderChild viewHolderChild;
        child = parentItems.get(groupPosition).getList().get(childPosition);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.content_menu_category_rv, null);
            viewHolderChild = new ViewHolderChild();

            viewHolderChild.tvSubCategoryName = convertView.findViewById(R.id.item);
            viewHolderChild.cbSubCategory = convertView.findViewById(R.id.menu_Cb);

            convertView.setTag(viewHolderChild);
        } else {
            viewHolderChild = (ViewHolderChild) convertView.getTag();
        }

        if (childItems.get(groupPosition).get(childPosition).isChecked()) {
            viewHolderChild.cbSubCategory.setChecked(true);
            notifyDataSetChanged();
        } else {
            viewHolderChild.cbSubCategory.setChecked(false);
            notifyDataSetChanged();
        }

        viewHolderChild.tvSubCategoryName.setText(child.getName());
        viewHolderChild.cbSubCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewHolderChild.cbSubCategory.isChecked()) {
                    count = 0;
                    parentItems.get(groupPosition).setChecked(true);
                    childItems.get(groupPosition).get(childPosition).setChecked(true);
                    notifyDataSetChanged();
                } else {
                    count = 0;
                    parentItems.get(groupPosition).setChecked(false);
                    childItems.get(groupPosition).get(childPosition).setChecked(false);
                    notifyDataSetChanged();
                }

                for (int i = 0; i < childItems.get(groupPosition).size(); i++) {
                    if (childItems.get(groupPosition).get(i).isChecked()) {
                        count++;
                    }
                }
                if (count == childItems.get(groupPosition).size()) {
                    parentItems.get(groupPosition).setChecked(true);
                    notifyDataSetChanged();
                } else {
                    parentItems.get(groupPosition).setChecked(false);
                    notifyDataSetChanged();
                }


                constants.Lists.childItems = childItems;
                constants.Lists.parentItems = parentItems;
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }



    private class ViewHolderParent {

        TextView tvMainCategoryName;
        CheckBox cbMainCategory;

    }

    private class ViewHolderChild {

        TextView tvSubCategoryName;
        CheckBox cbSubCategory;
        View viewDivider;
    }


}
