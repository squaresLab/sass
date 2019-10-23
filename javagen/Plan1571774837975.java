public class Plan1571774837975 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( StartServer("A") ) {
StartServer("B");
} else {
StartServer("A");
}

if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
StartServer("A");
}

StartServer("B");


StartServer("C");

}

}
}
