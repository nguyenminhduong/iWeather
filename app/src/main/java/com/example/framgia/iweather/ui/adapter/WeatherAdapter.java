package com.example.framgia.iweather.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.framgia.iweather.R;
import com.example.framgia.iweather.data.model.forecast.DataOfWeatherDate;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherAdapter
    extends RecyclerView.Adapter<WeatherAdapter.MyViewHolder> {
    private List<DataOfWeatherDate> mListWeathers;

    public WeatherAdapter(List<DataOfWeatherDate> list) {
        mListWeathers = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.items_recycleview, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bindData(mListWeathers.get(position));
    }

    @Override
    public int getItemCount() {
        return mListWeathers != null ? mListWeathers.size() : 0;
    }

    private void setWeatherIcon(ImageView imageView, String iconName) {
        switch (iconName) {
            case Const.CLEAR_NIGHT:
                imageView.setImageResource(R.drawable.ic_clear_night);
                break;
            case Const.CLOUDY:
                imageView.setImageResource(R.drawable.ic_cloudy);
                break;
            case Const.FOG:
                imageView.setImageResource(R.drawable.ic_fog);
                break;
            case Const.PARTLY_CLOUDY_DAY:
                imageView.setImageResource(R.drawable.ic_partly_cloudy_day);
                break;
            case Const.PARTLY_CLOUDY_NIGHT:
                imageView.setImageResource(R.drawable.ic_partly_cloudy_night);
                break;
            case Const.RAIN:
                imageView.setImageResource(R.drawable.ic_rain);
                break;
            case Const.SNOW:
                imageView.setImageResource(R.drawable.ic_snow);
                break;
            case Const.SLEET:
                imageView.setImageResource(R.drawable.ic_sleet);
                break;
            case Const.WIND:
                imageView.setImageResource(R.drawable.ic_wind);
                break;
            default:
                imageView.setImageResource(R.drawable.ic_clear_day);
                break;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_date)
        TextView mDate;
        @BindView(R.id.text_temperaturemax)
        TextView mTemperatureMax;
        @BindView(R.id.text_temperaturemin)
        TextView mTemperatureMin;
        @BindView(R.id.image_weather)
        ImageView mImageView;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bindData(DataOfWeatherDate dataOfWeatherDate) {
            mDate.setText(dataOfWeatherDate.getTime());
            mTemperatureMax.setText(Math.round(dataOfWeatherDate.getTemperatureMax()) + "");
            mTemperatureMin.setText(Math.round(dataOfWeatherDate.getTemperatureMin()) + "");
            setWeatherIcon(mImageView, dataOfWeatherDate.getIcon());
        }
    }
}