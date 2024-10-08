package services;

import models.SpotySong;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LoadSongsService {
    public ArrayList<SpotySong> loadFromCsv(Path path){
        //array de canciones que devuelve el servicio
        ArrayList<SpotySong> songs = new ArrayList<>();
        //array para almacenar las líneas del csv
        ArrayList<String> songsCsv;
        //canción auxiliar para cargar la información y añadirla al array
        SpotySong songAux;
        //número de artistas para crear el String[] del tamaño adecuado
        Integer artistsNum=1;
        //Para dar formato a la fecha
        //definimos el formato en que le vamos a pasar el String con la fecha
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");

        try {
            //leemos el archivo capturando las posibles excepciones
            songsCsv = (ArrayList<String>) Files.readAllLines(path);
            //quitamos la línea con los nombres de los campos
            songsCsv.removeFirst();
        } catch (IOException e) {
            //esto habría que gestionarlo bien
            throw new RuntimeException(e);
        }
        for (String song : songsCsv) {
            songAux = new SpotySong();
            //separamos por las comas que están rodeadas de comillas únicamente, distinguiendo de otras comas
            String[] csvArray = song.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            //cogemos el nombre de la canción de la primera posición del array
            songAux.setSongName(csvArray[0].replace("\"",""));
            artistsNum = Integer.parseInt(csvArray[2]);
            //creamos el array de artistas de la longitud adecuada e introducimos los nombres
            songAux.setArtistsNames(csvArray[1].split(",", artistsNum));
            //quitamos las comillas del array (sólo cuando hay  más de un artista):
            if (songAux.getArtistsNames().length>1){
                for (int i = 0; i < songAux.getArtistsNames().length; i++) {
                    songAux.getArtistsNames()[i] = songAux.getArtistsNames()[i].replace("\"","");
                }
            }
            try {
                //construimos la fecha a pertir de los Strings del CSV acorde al formato definido arriba
                songAux.setReleaseDate(format.parse(csvArray[3]+"/"+csvArray[4]+"/"+csvArray[5]));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            //cogemos los bpms
            songAux.setBpm(Integer.valueOf(csvArray[14]));
            songAux.setCoverUrl(csvArray[csvArray.length-1]);
            songs.add(songAux);
        }
        return songs;
    }
}
