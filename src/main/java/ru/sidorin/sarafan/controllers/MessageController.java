package ru.sidorin.sarafan.controllers;




import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import ru.sidorin.sarafan.domain.Message;
import ru.sidorin.sarafan.domain.Views;

import java.time.LocalDateTime;
import ru.sidorin.sarafan.repository.MessageRepo;

@RestController
@RequestMapping("message")
public class MessageController {
	
	
	
	private final MessageRepo messageRepo;
	
	@Autowired
	public MessageController(MessageRepo messageRepo) {
		super();
		this.messageRepo = messageRepo;
	}

	@GetMapping
    @JsonView(Views.IdName.class)
	public List<Message> list() {
		return (List<Message>) messageRepo.findAll();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("{id}")
	@JsonView(Views.FullMessage.class)
	public Message getOne(@PathVariable("id") Message message){
		return message;
	}

	
	
	@SuppressWarnings("unchecked")
	@PostMapping
	public Message create(@RequestBody Message message){
		message.setCreationDate(LocalDateTime.now());
		return messageRepo.save(message);
	}
	
	@SuppressWarnings("unchecked")
	@PutMapping("{id}")
    public Message update(
            @PathVariable("id") Message messageFromDb,
            @RequestBody Message message
    ) {
        BeanUtils.copyProperties(message, messageFromDb, "id");

        return messageRepo.save(messageFromDb);
    }
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Message message) {
		messageRepo.delete(message);
	}
	

}