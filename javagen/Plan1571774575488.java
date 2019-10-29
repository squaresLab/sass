public class Plan1571774575488 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("C");
}

StartServer("C");
if ( IncreaseTraffic("B") ) {
DecreaseDimmer("C");
} else {
StartServer("B");
StartServer("C");
for (int i = 0; i < 3 ; i++) {
StartServer("B");
DecreaseTraffic("A");

}



for (int i = 0; i < 4 ; i++) {
StartServer("A");
}


}



}
}
