public class Plan1571770081519 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
StartServer("B");
for (int i = 0; i < 3 ; i++) {
StartServer("C");
}


for (int i = 0; i < 2 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("A");
StartServer("B");
for (int i = 0; i < 2 ; i++) {
StartServer("C");
}



} else {
for (int i = 0; i < 2 ; i++) {
IncreaseTraffic("B");
}

}

if ( StartServer("A") ) {
StartServer("B");
} else {
StartServer("A");
}


}



}
}
