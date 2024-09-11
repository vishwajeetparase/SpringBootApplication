package com.artist.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.artist.dto.ArtistRequest;
import com.artist.model.Artist;
import com.artist.repository.ArtistRepository;
import com.artist.service.ArtistService;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public Artist createArtist(ArtistRequest artistRequest) {
        Artist artist = new Artist();
        artist.setFirstName(artistRequest.getFirstName());
        artist.setLastName(artistRequest.getLastName());
        return artistRepository.save(artist);
    }

    @Override
    public List<Artist> getArtists() {
        return artistRepository.findAllByOrderByIdDesc();
    }

    @Override
    public Artist getArtistByID(Long id) {
        return artistRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteArtist(Long id) {
        if (artistRepository.existsById(id)) {
            artistRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Artist not found with id: " + id);
        }
    }
}
