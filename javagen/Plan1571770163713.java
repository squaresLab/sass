public class Plan1571770163713 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
StartServer("B");
DecreaseTraffic("A");

StartServer("A");

} else {
StartServer("B");
}


}

}
}
