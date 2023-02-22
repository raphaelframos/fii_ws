package com.raphaelframos.refii.comment;

import com.raphaelframos.refii.common.entity.FundEntity;
import com.raphaelframos.refii.common.entity.ProfileEntity;
import com.raphaelframos.refii.fund.repository.FundRepository;
import com.raphaelframos.refii.profile.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private final CommentRepository repository;
    @Autowired
    private final ProfileRepository profileRepository;
    @Autowired
    private final FundRepository fundRepository;

    public CommentService(CommentRepository repository, ProfileRepository profileRepository, FundRepository fundRepository) {
        this.repository = repository;
        this.profileRepository = profileRepository;
        this.fundRepository = fundRepository;
    }

    public List<Comment> commentsBy(Long userId, Long fundId) {
        return repository.findMyComments(userId, fundId);
    }

    public List<Comment> find(Long fundId) {
        return repository.find(fundId);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public int create(String message, Long userId, Long fundId) {
        Optional<ProfileEntity> profileEntity = profileRepository.findById(userId);
        Optional<FundEntity> fundEntity = fundRepository.findById(fundId);
        try {
            FundEntity fund = fundEntity.get();
            Comment comment = new Comment();
            comment.setMessage(message);
            comment.setProfile(profileEntity.get());
            comment.setFund(fund);
            comment = repository.save(comment);
            fund.add(comment);
            fundRepository.save(fund);
            return HttpStatus.CREATED.value();
        } catch (Exception e) {
            return HttpStatus.NOT_FOUND.value();
        }
    }

    public String newComment(Long fundId, Long userId) {
        Optional<FundEntity> fundEntity = fundRepository.findById(fundId);
        Optional<Comment> comment = repository.lastComment(userId, fundId);
        String result = "";
        if (fundEntity.isPresent()) {
            FundEntity fund = fundEntity.get();
            result = comment.map(value -> "Seu último comentário sobre o " + fund.getSymbol() + " foi: " + value.getMessage() +
                    "\no que deseja acrescentar?").orElseGet(() -> "O que você pensa sobre o " + fund.getSymbol() + "?");
        }
        return result;
    }
}
