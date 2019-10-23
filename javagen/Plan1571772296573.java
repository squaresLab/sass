public class Plan1571772296573 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
for (int i = 0; i < 5 ; i++) {
StartServer("A");
}


StartServer("B");
for (int i = 0; i < 4 ; i++) {
StartServer("C");
}

DecreaseDimmer("C");



for (int i = 0; i < 2 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
StartServer("B");
}

}


}
}
