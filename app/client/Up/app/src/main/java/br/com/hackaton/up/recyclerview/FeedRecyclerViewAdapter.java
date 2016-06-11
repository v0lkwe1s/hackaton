package br.com.hackaton.up.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import br.com.hackaton.up.R;
import br.com.hackaton.up.model.Feed;
import br.com.hackaton.up.model.Results;
import br.com.hackaton.up.utils.CustomJsonDateDeserializer;

public class FeedRecyclerViewAdapter extends RecyclerView.Adapter<FeedRecyclerViewAdapter.DataObjectHolder> {

    private SimpleDateFormat formatter = CustomJsonDateDeserializer.format;
    private Context mContext;
    private Results feedDataSet;
    private int posCardView = 0;

    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        TextView date;
        TextView description;
        Button btnSeeMore;

        public DataObjectHolder(View itemView){
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            date = (TextView) itemView.findViewById(R.id.date);
            description = (TextView) itemView.findViewById(R.id.description);
            btnSeeMore = (Button) itemView.findViewById(R.id.btnSeeMore);
        }

        @Override
        public void onClick(View v) {}
    }

    public FeedRecyclerViewAdapter(Results feedDataSet, Context context){
        this.feedDataSet = feedDataSet;
        this.mContext = context;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_row, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.title.setText(feedDataSet.getResults().get(position).getTitle());
        holder.date.setText(formatter.format(feedDataSet.getResults().get(position).getDate()));
        holder.description.setText(feedDataSet.getResults().get(position).getDescription());
        posCardView = position;
        holder.btnSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(feedDataSet.getResults().get(posCardView).getLink());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return feedDataSet.getResults().size();
    }

    public void addFeed(Feed feed, int index){
        feedDataSet.getResults().add(index, feed);
    }

    public void deleteFeed(int index){
        feedDataSet.getResults().remove(index);
    }
}
