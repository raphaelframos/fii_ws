package com.raphaelframos.refii.profile;

import com.raphaelframos.refii.common.entity.Profile;
import com.raphaelframos.refii.common.model.ChatResponse;
import com.raphaelframos.refii.profile.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private final ProfileRepository repository;

    public ProfileService(ProfileRepository repository) {
        this.repository = repository;
    }

    public Optional<Profile> findBy(Long id) {
        return repository.findById(id);
    }

    public ChatResponse create(Long id, String value, int position) {
        ChatResponse chatResponse = new ChatResponse();
        if(position == 0){
            ++position;
            chatResponse.setText("Olá, meu nome é Joe e vou ser seu assistente financeiro. Para comecar nossa história, qual é o seu nome?");
        }else if(position == 1){
            if(value.isEmpty()){
                chatResponse.setText("Não me abandone! Qual seu nome?");
            }else{
                ++position;
                chatResponse.setText("Tudo bem, " + value + "! Me diga qual é sua profissão?");
            }
        }
        chatResponse.setPosition(position);
        return chatResponse;
    }

    public Long init(String email) {
        Optional<Profile> entity = repository.findByEmail(email);
        Long id;
        if(entity.isPresent()){
            id = entity.get().getId();
        } else {
            id = create(email);
        }
        return id;
    }

    private Long create(String email) {
        Profile profile = new Profile(email);
        return repository.save(profile).getId();
    }
}
