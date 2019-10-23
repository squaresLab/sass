public class Plan1571775064305 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
for (int i = 0; i < 4 ; i++) {
if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
DecreaseDimmer("C");
}

StartServer("A");

}

for (int i = 0; i < 4 ; i++) {
StartServer("C");
}



}
}
