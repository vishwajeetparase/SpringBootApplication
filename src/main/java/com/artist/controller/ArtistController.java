package com.artist.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artist.dto.ArtistRequest;
import com.artist.model.Artist;
import com.artist.service.ArtistService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("v1/artists")
public class ArtistController {
    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/home")
    public String home(){
        return "index";
    }


    @PostMapping("/create")
    public ResponseEntity<Artist> createPlayList(@RequestBody ArtistRequest artistRequest){
        Artist createdArtist = artistService.createArtist(artistRequest);
        return new ResponseEntity<>(createdArtist, HttpStatus.CREATED);
    }

    @GetMapping("/{artistId}")
    public ResponseEntity<Artist> getArtistById(@PathVariable Long artistId){
        Artist artist = artistService.getArtistByID(artistId);
        if(artist != null){
            return new ResponseEntity<>(artist,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<List<Artist>> getAllArtists(){
        List<Artist> artists = artistService.getArtists();
        if(artists!=null){
            return new ResponseEntity<>(artists,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{artistId}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long artistId){
        try {
            artistService.deleteArtist(artistId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
