public class Plan1571773415632 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("C");
StartServer("B");

}

} else {
StartServer("C");
StartServer("B");

}

}

StartServer("A");

StartServer("A");

}
}
