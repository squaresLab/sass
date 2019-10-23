public class Plan1571768700848 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
for (int i = 0; i < 2 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("A");
} else {
for (int i = 0; i < 3 ; i++) {

}

}

StartServer("C");

StartServer("B");

}

StartServer("B");
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

} else {
StartServer("B");
}

DecreaseTraffic("A");

StartServer("B");




}
}
