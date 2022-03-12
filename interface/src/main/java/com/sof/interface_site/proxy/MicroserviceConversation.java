package com.sof.interface_site.proxy;

import com.sof.interface_site.model.Conversation;
import com.sof.interface_site.model.ConversationBDD;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

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
    ConversationBDD saveConversation(@RequestBody ConversationBDD conversation);

    @DeleteMapping(value="/supprimerConversationsUtilisateur/{idUtilisateur}")
    boolean supprimerConversationsUtilisateur(@PathVariable int idUtilisateur);
}
