package com.kobe.nucleus.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class RemiseClient extends Remise implements Serializable {

	private static final long serialVersionUID = 1L;
    @OneToMany(mappedBy = "remise")
    private Set<Client> clients = new HashSet<>();
    
    
    public Set<Client> getClients() {
        return clients;
    }

    public Remise clients(Set<Client> clients) {
        this.clients = clients;
        return this;
    }

    public Remise addClient(Client client) {
        this.clients.add(client);
        client.setRemise(this);
        return this;
    }
    

    public Remise removeClient(Client client) {
        this.clients.remove(client);
        client.setRemise(null);
        return this;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    
    
}
