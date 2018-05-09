public class Plan1525889274542 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
StartServer("C");

StartServer("A");
for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
StartServer("A");

} else {

}

}


StartServer("A");


for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
StartServer("B");

}


}
}
