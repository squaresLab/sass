public class Plan1571771901613 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
for (int i = 0; i < 2 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
} else {
StartServer("A");
}

}

StartServer("B");
DecreaseTraffic("A");


}

}
}
