public class Plan1571774023711 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
StartServer("C");
}

}

StartServer("B");

for (int i = 0; i < 3 ; i++) {
StartServer("B");
StartServer("A");

}


}
}
