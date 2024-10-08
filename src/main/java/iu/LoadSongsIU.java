package iu;

import models.SpotySong;
import services.LoadSongsService;

import java.nio.file.Path;
import java.util.ArrayList;

public class LoadSongsIU {
    private LoadSongsService loadSongsService = new LoadSongsService();

    public void showSpotySongs() {
        //identifico la ruta al archivo con las canciones
        Path ruta = Path.of("src/main/resources/Spotify Most Streamed Songs.csv");

        //variable para almacenar las canciones acorde a mi modelo.
        ArrayList<SpotySong> spotySongs = loadSongsService.loadFromCsv(ruta);
        for (SpotySong spotySong : spotySongs) {
            System.out.print(spotySong.getSongName() + ". " + spotySong.getArtistsNames().length + " artist(s): ");
            for (int i = 0; i < spotySong.getArtistsNames().length; i++) {
                System.out.print(spotySong.getArtistsNames()[i] + ". ");
            }
            System.out.print(spotySong.getReleaseDate());
            System.out.print(". BPM: "+spotySong.getBpm());
            System.out.println(". Cover: " + spotySong.getCoverUrl());
        }
    }
}
