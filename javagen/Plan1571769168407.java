public class Plan1571769168407 extends Plan { 
public static void main(String[] args) { 
StartServer("C");
if ( StartServer("C") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

} else {
StartServer("C");
}

for (int i = 0; i < 5 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
StartServer("A");
} else {
DecreaseTraffic("A");
}


}



}
}
