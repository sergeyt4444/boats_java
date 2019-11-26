package socket_manager;

import full_map.*;

public interface abstract_socket_manager {
    public void createListeners();
    public void sendAllClients(full_map fm);
}
