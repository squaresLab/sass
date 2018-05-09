public class Plan1525889181896 extends Plan { 
public static void main(String[] args) { 
StartServer("C");
if ( StartServer("A") ) {
StartServer("C");
} else {
StartServer("A");
}

StartServer("C");



for (int i = 0; i < 3 ; i++) {
StartServer("A");
if ( DecreaseTraffic("A") ) {
StartServer("C");
} else {
DecreaseTraffic("C");
}


}



for (int i = 0; i < 4 ; i++) {
StartServer("B");
}


}
}
