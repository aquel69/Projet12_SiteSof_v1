package com.sof.interface_site.proxy;

import com.sof.interface_site.model.Conversation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "microservice-conversation", url = "localhost:9094")
public interface MicroserviceConversation {

    @GetMapping(value = "/toutesLesConversations")
    List<Conversation> findAllConversation();

    @GetMapping(value = "/conversationsSelonMembre/{idMembre}")
    List<Conversation> conversationsSelonMembre(@PathVariable int idMembre);

    @GetMapping(value = "/conversationsSelonMembre")
    List<Conversation> conversationSelonDateAjoutPourListeMembre();

    @PostMapping(value = "/ajouterConversation")
    Conversation saveConversation(@RequestBody Conversation conversation);
}
