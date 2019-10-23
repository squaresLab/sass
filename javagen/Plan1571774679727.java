public class Plan1571774679727 extends Plan { 
public static void main(String[] args) { 
StartServer("C");
StartServer("B");
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}



if ( StartServer("A") ) {
StartServer("A");
} else {
for (int i = 0; i < 2 ; i++) {

}

}


StartServer("B");

StartServer("A");
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("A");
}

} else {
StartServer("C");
}



StartServer("B");
DecreaseTraffic("A");


}
}
