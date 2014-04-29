package message;

import graphicInterfaces.ChatWindow;

public class SignOutMessage implements Message {
	// ca si server vreau sa stiu pe cine deconectez
	// sign out-ul poate fi doar cu succes; eventuala exceptie se trateaza in
	// server

	private static final long serialVersionUID = 1L;
	private ChatWindow chatWindow;
	@SuppressWarnings("unused")
	private String name;

	public SignOutMessage() {
	}

	public void interactOnServer() {

	}

	public void interactOnClient() {
		
	}

	public void interactOnClient(ChatWindow chatWindow) {

	}
	
	public void setName(String name){
		this.name = name;
	}

	public void setChatWindow(ChatWindow chatWindow) {
		this.chatWindow = chatWindow;
	}

	public ChatWindow getChatWindow() {
		return chatWindow;
	}

}

 
// -8 1 2 23 10 11 0 7 20 13 
//  int c = 0; poz_maxima = 0
//if a[j]

// 0-60
/*
for i = 1 i < n i ++
	{
	j = i + 1;
	nrPozNevizitate = 0;
	for z = i+1 z<=n; z++
		if viz[i] == 0 
			nrPozNevizitate ++;
	while nrPozNevizitate != 0
		l = 1
		while j != n
			{if a[j] > a[i]
				contor++;
				v2[l] = a[j];
				l++;
			j++;
			}
		if contor > contorMax
			{
				contorMax = contor;
				o = 1;
				while(o != l-1)
					v2max[o] = v2[o];
					o++;
			}

*
*/
// 0 30 31 1 2 3 
// 0 30 31 32 33 1 2 
//       0 11 33 22 1 34 5 6 7   
