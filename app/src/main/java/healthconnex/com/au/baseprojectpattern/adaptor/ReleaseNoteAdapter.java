package healthconnex.com.au.baseprojectpattern.adaptor;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import healthconnex.com.au.baseprojectpattern.base.Config;
import healthconnex.com.au.baseprojectpattern.model.ReleaseNoteItem;
import healthconnex.com.au.baseprojectpattern.R;

/**
 * Created by frincon on 12/02/2015.
 */
public class ReleaseNoteAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<ReleaseNoteItem> dataSource;

    private LayoutInflater inflater = null;
    //private final ImageLoaderCustom mImageLoader;

    /**
     * ********* Declare the types **************
     */
    public static final int TYPE_NEW = 0;
    public static final int TYPE_FIX = 1;
    public static final int TYPE_IMPR = 2;
    public static final int numTypes = 3;


    public ReleaseNoteAdapter(Activity activity, ArrayList<ReleaseNoteItem> dataSource){

        this.activity = activity;
        this.dataSource = dataSource;

        /***********  Layout inflator to call external xml layout () ***********/
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //mImageLoader = new ImageLoaderCustom(activity, R.drawable.ic_no_image_car);
    }

    public int getCount() {
        return dataSource.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public int getViewTypeCount() {
        return numTypes;
    }

    @Override
    public int getItemViewType(int position) {
        ReleaseNoteItem item = (ReleaseNoteItem) dataSource.get(position);

        if (item.getType().equals(Config.RELEASE_ITEM_NEW)) {
            return TYPE_NEW;
        }

        if (item.getType().equals(Config.RELEASE_ITEM_FIX)) {
            return TYPE_FIX;
        }

        if (item.getType().equals(Config.RELEASE_ITEM_IMPR)) {
            return TYPE_IMPR;
        }
        return TYPE_NEW;
    }

    /**
     * ****** Create a holder Class to contain inflated xml file elements ********
     */
    public static class ViewHolder {
        public ImageView imageViewType;
        public TextView textViewDescription;
    }

    /**
     * *** Depends upon data size called for each row , Create each ListView row ****
     */
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        int type = getItemViewType(position);

        if (convertView == null) {
            holder = new ViewHolder();
            switch (type) {
                case TYPE_NEW:
                    convertView = inflater.inflate(R.layout.release_note_item_cell, null);
                    holder.imageViewType = (ImageView) convertView.findViewById(R.id.imageViewType);
                    holder.textViewDescription = (TextView) convertView.findViewById(R.id.textViewDescription);
                    break;
                case TYPE_FIX:
                    convertView = inflater.inflate(R.layout.release_note_item_cell, null);
                    holder.imageViewType = (ImageView) convertView.findViewById(R.id.imageViewType);
                    holder.textViewDescription = (TextView) convertView.findViewById(R.id.textViewDescription);
                    break;
                case TYPE_IMPR:
                    convertView = inflater.inflate(R.layout.release_note_item_cell, null);
                    holder.imageViewType = (ImageView) convertView.findViewById(R.id.imageViewType);
                    holder.textViewDescription = (TextView) convertView.findViewById(R.id.textViewDescription);
                    break;
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        /***** Get each item object from Arraylist ********/
        ReleaseNoteItem  item  = (ReleaseNoteItem) dataSource.get(position);

        holder.textViewDescription.setText(item.getDescription());

        if (type == TYPE_NEW) {
            holder.imageViewType.setImageResource(R.drawable.ic_release_new);
            //mImageLoader.loadImage(tempValues.getUrlImage(), holder.imageView);
        }

        if (type == TYPE_FIX) {
            holder.imageViewType.setImageResource(R.drawable.ic_release_fix);
        }

        if (type == TYPE_IMPR) {
            holder.imageViewType.setImageResource(R.drawable.ic_release_impr);
        }

        return convertView;
    }

}
