public class Plan1571774589531 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("B");
StartServer("A");
StartServer("B");
StartServer("A");
DecreaseTraffic("A");






if ( StartServer("C") ) {
StartServer("C");
} else {
StartServer("C");
}


}

}
}
