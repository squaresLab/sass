public class Plan1571775792221 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
if ( StartServer("A") ) {
StartServer("B");
} else {
StartServer("B");
}

StartServer("C");


for (int i = 0; i < 6 ; i++) {
StartServer("B");
}

StartServer("C");

for (int i = 0; i < 3 ; i++) {
StartServer("C");
DecreaseTraffic("A");

}



}
}
