package com.esprit.mtdev.MusicBox.utilities.comparators;

import com.esprit.mtdev.MusicBox.models.LocalTrack;

import java.util.Comparator;

/**
 * Created by Harjot on 18-Jan-17.
 */

public class LocalMusicComparator implements Comparator<LocalTrack> {

    @Override
    public int compare(LocalTrack lhs, LocalTrack rhs) {
        return lhs.getTitle().toString().compareTo(rhs.getTitle().toString());
    }
}
