public class Plan1571774950799 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("C");
}

if ( StartServer("C") ) {
for (int i = 0; i < 6 ; i++) {
StartServer("A");
}

} else {
for (int i = 0; i < 5 ; i++) {
StartServer("A");
}

}

DecreaseTraffic("A");

DecreaseTraffic("A");
for (int i = 0; i < 5 ; i++) {
StartServer("B");
}


DecreaseDimmer("A");



}
}
