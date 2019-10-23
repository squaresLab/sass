public class Plan1571768255884 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
for (int i = 0; i < 4 ; i++) {
StartServer("B");
}

for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
if ( StartServer("C") ) {
if ( StartServer("C") ) {
StartServer("A");
} else {
StartServer("A");
}

} else {
StartServer("B");
}


}



}
}
