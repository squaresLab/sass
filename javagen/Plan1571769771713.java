public class Plan1571769771713 extends Plan { 
public static void main(String[] args) { 
StartServer("C");

StartServer("B");

for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

} else {
StartServer("B");
}

if ( DecreaseTraffic("A") ) {
StartServer("A");
} else {
StartServer("B");
}


}



}
}
