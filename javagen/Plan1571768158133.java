public class Plan1571768158133 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( StartServer("A") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("C");
}

for (int i = 0; i < 3 ; i++) {
if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("B");
}

}


StartServer("A");

} else {
StartServer("C");
StartServer("C");

StartServer("B");

}

}

}
}
