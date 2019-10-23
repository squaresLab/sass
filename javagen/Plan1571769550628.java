public class Plan1571769550628 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("A");
StartServer("A");
if ( StartServer("C") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

} else {
StartServer("A");
}

StartServer("B");
DecreaseTraffic("A");




if ( StartServer("C") ) {
StartServer("B");
} else {
StartServer("A");
}


}

}
}
