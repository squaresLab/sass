public class Plan1571772013817 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("B");
if ( DecreaseDimmer("B") ) {
StartServer("C");
} else {
DecreaseTraffic("A");
}


}

for (int i = 0; i < 4 ; i++) {
StartServer("B");
StartServer("A");

}

for (int i = 0; i < 4 ; i++) {
StartServer("C");
}



}
}
