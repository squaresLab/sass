public class Plan1571768454289 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
}

for (int i = 0; i < 2 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
StartServer("A");

} else {
StartServer("A");
}

StartServer("B");
if ( StartServer("A") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("C");
}

} else {
StartServer("A");
}



}


}
}
