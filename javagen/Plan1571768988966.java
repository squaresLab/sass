public class Plan1571768988966 extends Plan { 
public static void main(String[] args) { 
if ( StartServer("B") ) {
StartServer("A");
} else {
StartServer("B");
}

StartServer("C");

for (int i = 0; i < 3 ; i++) {
StartServer("B");
DecreaseTraffic("A");
StartServer("A");


StartServer("C");

}


}
}
