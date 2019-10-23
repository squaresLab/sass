public class Plan1571768998330 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( StartServer("A") ) {
StartServer("B");
} else {
StartServer("C");
}

StartServer("B");
StartServer("A");
StartServer("C");



DecreaseTraffic("A");

}

}
}
