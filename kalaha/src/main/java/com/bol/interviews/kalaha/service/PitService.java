package com.bol.interviews.kalaha.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bol.interviews.kalaha.model.Board;
import com.bol.interviews.kalaha.model.Pit;
import com.bol.interviews.kalaha.repository.PitRepository;
@Service
public class PitService {

	@Autowired
	private PitRepository pitRepository;
	
	public PitService() {
		// TODO Auto-generated constructor stub
	}
	
	public PitService(PitRepository pitRepositoryMock) {
		this.pitRepository = pitRepositoryMock;
	}

	public Pit createNewPit (Board board, Integer position, Integer value) {
		Pit pit = new Pit (board, position, value);
		pitRepository.save(pit);
		return pit;
	}
	
	public Pit updatePitNumberOfStones (Board board, Integer position, Integer newValue, boolean isToSum) {
		Optional<Pit> toMergePit = pitRepository.findByBoardAndPosition(board, position);		
		if (toMergePit.isPresent()) {
			if (isToSum) {
				toMergePit.get().setValue(toMergePit.get().getValue()+newValue);
			}
			else  {
				toMergePit.get().setValue(newValue);
			}
			
		}
		Pit result = pitRepository.save(toMergePit.get()); 
		return result;
	}
	
	public Pit updatePitIncrement (Board board, Integer position) {
		Optional<Pit> toMergePit = pitRepository.findByBoardAndPosition(board, position);	
		if (toMergePit.isPresent()) {
			int pitValue = toMergePit.get().getValue();
			pitValue++;
			toMergePit.get().setValue(pitValue);
			
		}
		Pit result = pitRepository.save(toMergePit.get()); 
		return result;
	}
	
	public Pit updatePitStealPit (Board board, Integer positionReceiving, Integer positionGiving) {
		Optional<Pit> toMergePitReceiving = pitRepository.findByBoardAndPosition(board, positionReceiving);	
		Optional<Pit> toMergePitGiving = pitRepository.findByBoardAndPosition(board, positionGiving);
		Pit newPit = toMergePitReceiving.get();
		if (toMergePitReceiving.isPresent() && toMergePitGiving.isPresent()) {
			newPit = updatePitNumberOfStones(board, positionReceiving, toMergePitGiving.get().getValue(), true);
			updatePitNumberOfStones(board, positionGiving, 0, false);
		}
		return newPit;
	}

	public int getPitNumberOfStonesByBoardAndPosition(Board board, int i) {
		int value =pitRepository.findByBoardAndPosition (board, i).get().getValue(); 
		return value;
	}
	

}
