public class Plan1571775284170 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("B");
StartServer("A");
StartServer("B");
StartServer("A");
if ( StartServer("C") ) {
StartServer("A");
} else {
StartServer("A");
}



if ( StartServer("B") ) {
StartServer("C");
} else {
IncreaseTraffic("C");
}




if ( DecreaseTraffic("A") ) {
StartServer("C");
} else {
IncreaseTraffic("C");
}


}

}
}
