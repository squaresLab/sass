public class Plan1571771785817 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( StartServer("A") ) {
StartServer("C");
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}


} else {
StartServer("B");
}

StartServer("B");
if ( StartServer("C") ) {
StartServer("A");
} else {
StartServer("A");
}


DecreaseTraffic("A");


}

}
}
