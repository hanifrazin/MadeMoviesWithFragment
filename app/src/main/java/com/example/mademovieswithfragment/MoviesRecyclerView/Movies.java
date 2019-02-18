package com.example.mademovieswithfragment.MoviesRecyclerView;

import android.os.Parcel;
import android.os.Parcelable;

public class Movies implements Parcelable {
    private String judul;
    private String tanggal;
    private String deskripsi;
    private int Poster;

    public Movies() {
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public int getPoster() {
        return Poster;
    }

    public void setPoster(int poster) {
        Poster = poster;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.judul);
        dest.writeString(this.tanggal);
        dest.writeString(this.deskripsi);
        dest.writeInt(this.Poster);
    }

    protected Movies(Parcel in) {
        this.judul = in.readString();
        this.tanggal = in.readString();
        this.deskripsi = in.readString();
        this.Poster = in.readInt();
    }

    public static final Parcelable.Creator<Movies> CREATOR = new Parcelable.Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel source) {
            return new Movies(source);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };
}
