public class Plan1571769542036 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
StartServer("C");
} else {
StartServer("B");
}

StartServer("A");


StartServer("C");

DecreaseTraffic("A");

}

}
}
